import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ParkingspaceemptyRoutingModule } from './parkingspaceempty-routing.module';
import { ParkingspaceemptyComponent } from './parkingspaceempty/parkingspaceempty.component';



@NgModule({
  declarations: [
    ParkingspaceemptyComponent
  ],
  imports: [
    CommonModule,
    ParkingspaceemptyRoutingModule,
  ]
})
export class ParkingspaceemptyModule { }
