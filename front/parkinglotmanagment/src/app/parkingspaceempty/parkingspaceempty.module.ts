import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ParkingspaceemptyRoutingModule } from './parkingspaceempty-routing.module';
import { ParkingspaceemptyComponent } from './parkingspaceempty/parkingspaceempty.component';
import { MatTableModule } from '@angular/material/table';



@NgModule({
  declarations: [
    ParkingspaceemptyComponent
  ],
  imports: [
    CommonModule,
    ParkingspaceemptyRoutingModule,
    MatTableModule
  ]
})
export class ParkingspaceemptyModule { }
