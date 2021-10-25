import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ApiService } from '../core/services/api.service';
import { Supplier } from '../model/supplier';

@Component({
  selector: 'app-suppliers',
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.scss']
})
export class SuppliersComponent implements OnInit {

  suppliers: Supplier[];
  suppliersSub: Subscription;

  constructor(private http: ApiService) { }

  ngOnInit(): void {
    this.getAllSuppliers();
  }

  getAllSuppliers(): void{
    this.suppliersSub = this.http.get("/suppliers").subscribe(
      (value: any) => {
        console.log(value)
        this.suppliers = value._embedded.suppliers;
      },
      (error) => {
        console.log(error)
      }
    );
  }

  ngOnDestroy(): void {
    if(this.suppliersSub) this.suppliersSub.unsubscribe();
  }

}
