import { Injectable } from '@angular/core';
import { CartItem } from '../models/cart-item';
import { Subject, VirtualTimeScheduler } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CartService {
  
  cartItems: CartItem[] = [];

  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor() { }

  addToCart(theCartItem: CartItem){

    // check if we already have the item in out cart
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem = undefined;

    if(this.cartItems.length > 0){
      // find the item in the cart basen on item id
      existingCartItem = this.cartItems.find(tempCartItem => tempCartItem.id === theCartItem.id);

      // check if we found it.
      alreadyExistsInCart = (existingCartItem != undefined);
    }

    if(alreadyExistsInCart){
      // increment the quantity
      existingCartItem.quantity++;
    }
    else{
      this.cartItems.push(theCartItem);
    }

    // compute cart total price and total quantity
    this.computeCartTotals();

  }
  computeCartTotals() {
    
    let totolPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for(let currentCartItem of this.cartItems){
      totolPriceValue += currentCartItem.quantity * currentCartItem.unitPrice;
      totalQuantityValue += currentCartItem.quantity;
    }

    // publish the new values.. all subscribers qill receive the new data
    this.totalPrice.next(totolPriceValue); // next() method publis/send event
    this.totalQuantity.next(totalQuantityValue);

    this.logCartItems(totolPriceValue,totalQuantityValue);

  }
  logCartItems(totolPriceValue: number, totalQuantityValue: number) {
    
    for(let tempCartItem of this.cartItems){
      console.log(`name: ${tempCartItem.name} unitPrie: ${tempCartItem.unitPrice} subTotalPrice: ${tempCartItem.quantity*tempCartItem.unitPrice}`);
  
    }
    console.log(`totalPrice: ${totolPriceValue} totalQuantity: ${totalQuantityValue}`);
    console.log("-----");
  }

  decrementQuantity(theCartItem: CartItem) {
    theCartItem.quantity--;

    if(theCartItem.quantity === 0){
      this.remove(theCartItem);
    }
    else{
      this.computeCartTotals();
    }
  }
  remove(theCartItem: CartItem) {
    // get index ot the item in the array
    const itemIndex = this.cartItems.findIndex(temp => temp.id = theCartItem.id);

    if(itemIndex > -1){
      this.cartItems.splice(itemIndex,1);
      this.computeCartTotals();
    }
  }
  
}
