import {Component, inject,} from "@angular/core";
import {RouterModule} from "@angular/router";
import {SplitterModule} from 'primeng/splitter';
import {ToolbarModule} from 'primeng/toolbar';
import {PanelMenuComponent} from "./shared/ui/panel-menu/panel-menu.component";
import {ToastModule} from "primeng/toast";
import {CartComponent} from "./cart/features/cart/cart.component";
import {Button} from "primeng/button";
import {CartService} from "./shared/services/cart.service";
import {DialogModule} from "primeng/dialog";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, ToastModule, CartComponent, Button, DialogModule],
})
export class AppComponent {
  private readonly _cartService = inject(CartService);
  showCartDialog: boolean = false;
  showFavDialog: boolean = false;
  title = "ALTEN SHOP";

  get countCartItems() {
    return this._cartService.getCountItems();
  }

  openCart(): void {
    this.showCartDialog = true;
  }

  closeCart(): void {
    this.showCartDialog = false;
  }

  openFav(): void {
    this.showFavDialog = true;
  }

  closeFav(): void {
    this.showFavDialog = false;
  }
}
