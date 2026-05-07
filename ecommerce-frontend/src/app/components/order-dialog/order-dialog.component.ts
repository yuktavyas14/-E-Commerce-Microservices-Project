import { Component, Inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { OrderService } from '../../services/order.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-order-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  templateUrl: './order-dialog.component.html',
  styleUrls: ['./order-dialog.component.css']
})
export class OrderDialogComponent {
  orderForm: FormGroup;
  loading = signal(false);

  constructor(
    private fb: FormBuilder,
    private orderService: OrderService,
    private dialogRef: MatDialogRef<OrderDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public product: Product,
    private snackBar: MatSnackBar
  ) {
    this.orderForm = this.fb.group({
      quantity: [1, [Validators.required, Validators.min(1)]]
    });
  }

  get totalPrice(): number {
    const quantity = this.orderForm.get('quantity')?.value || 1;
    return this.product.price * quantity;
  }

  onSubmit(): void {
    if (this.orderForm.valid) {
      this.loading.set(true);
      const orderData = {
        productId: this.product.id!,
        quantity: this.orderForm.value.quantity,
        status: 'PENDING'
      };

      this.orderService.placeOrder(orderData).subscribe({
        next: () => {
          this.loading.set(false);
          this.snackBar.open('Order placed successfully!', 'Close', { duration: 3000 });
          this.dialogRef.close(true);
        },
        error: (error) => {
          this.loading.set(false);
          this.snackBar.open(error.error || 'Failed to place order', 'Close', { duration: 3000 });
        }
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }
}

