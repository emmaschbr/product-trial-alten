import {Injectable, signal, WritableSignal} from "@angular/core";
import {Cart} from "../../cart/data-access/cart.model";
import {Product} from "../../products/data-access/product.model";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  cartState: WritableSignal<Cart> = signal<Cart>({items: [], total: 0});
  public readonly cart = this.cartState.asReadonly();

  private updateCart(cart: Cart): void {
    cart.total = cart.items.reduce((sum, item) =>
      sum + (item.product.price * item.quantity), 0);
    this.cartState.set({...cart});
  }

  public addToCart(product: Product, quantity: number = 1): void {
    const currentCart = this.cartState();
    const item = currentCart.items.find((item) => item.product.id === product.id);

    if (item) {
      item.quantity += quantity;
    } else {
      currentCart.items.push({product, quantity});
    }
    this.updateCart(currentCart);
  }

  public removeFromCart(productId: number): void {
    const currentCart = this.cartState();
    currentCart.items = currentCart.items.filter(item => item.product.id !== productId);
    this.updateCart(currentCart);
  }

  getCountItems(): number {
    return this.cartState().items.reduce((acc, item) => acc + item.quantity, 0);
  }
}
