import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';

import { ParkingspaceemptyRoutingModule } from './parkingspaceempty-routing.module';
import { ParkingspaceemptyComponent } from './parkingspaceempty/parkingspaceempty.component';
import { MatPaginatorModule } from '@angular/material/paginator';




@NgModule({
  declarations: [
    ParkingspaceemptyComponent
  ],
  imports: [
    CommonModule,
    ParkingspaceemptyRoutingModule,
    MatTableModule,
    MatCardModule,
    MatPaginatorModule
  ]
})
export class ParkingspaceemptyModule { }
