import {Product} from "../../products/data-access/product.model";

export interface Cart {
  items: CartItem[];
  total: number;
}

export interface CartItem {
  product: Product;
  quantity: number;
}
