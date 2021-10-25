import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  products: any = [
    {id: 1, name:"HP pavillon", price: 1200},
    {id: 2, name:"Ordinateur Dell pro", price: 1600},
    {id: 3, name:"mac book pro", price: 2350}
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
