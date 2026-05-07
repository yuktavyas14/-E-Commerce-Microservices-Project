import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginRequest, RegisterRequest, User } from '../models/user.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly API_URL = `${environment.apiUrl}/users`;
  private currentUserSignal = signal<User | null>(null);
  private isAuthenticatedSignal = signal<boolean>(false);

  currentUser = this.currentUserSignal.asReadonly();
  isAuthenticated = this.isAuthenticatedSignal.asReadonly();

  constructor(private http: HttpClient) {
    this.checkAuth();
  }

  private checkAuth(): void {
    const token = this.getToken();
    if (token) {
      const user = this.getUserFromToken(token);
      if (user) {
        this.currentUserSignal.set(user);
        this.isAuthenticatedSignal.set(true);
      }
    }
  }

  register(request: RegisterRequest): Observable<User> {
    return this.http.post<User>(`${this.API_URL}/register`, request);
  }

  login(request: LoginRequest): Observable<string> {
    return this.http.post<string>(`${this.API_URL}/login`, request, {
      responseType: 'text' as 'json'
    }).pipe(
      tap(token => {
        this.setToken(token);
        const user = this.getUserFromToken(token);
        if (user) {
          this.currentUserSignal.set(user);
          this.isAuthenticatedSignal.set(true);
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.currentUserSignal.set(null);
    this.isAuthenticatedSignal.set(false);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  private setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  private getUserFromToken(token: string): User | null {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return {
        username: payload.sub,
        role: payload.role
      };
    } catch (error) {
      return null;
    }
  }

  isAdmin(): boolean {
    const user = this.currentUserSignal();
    return user?.role === 'ADMIN';
  }

  getUsername(): string | null {
    return this.currentUserSignal()?.username || null;
  }
}

