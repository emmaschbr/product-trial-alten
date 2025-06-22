import {Component, inject} from "@angular/core";
import {CartService} from "../../../shared/services/cart.service";
import {TableModule} from "primeng/table";
import {CurrencyPipe} from "@angular/common";
import {Button} from "primeng/button";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    TableModule,
    CurrencyPipe,
    Button
  ],
  templateUrl: './favorites.component.html'
})

export class FavoritesComponent {
  private readonly _cartService = inject(CartService);
  public readonly cart = this._cartService.cart;

  removeItem(productId: number){
    this._cartService.removeFromCart(productId);
  }
}
