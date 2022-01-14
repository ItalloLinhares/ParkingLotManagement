import { ParkingspaceemptyComponent } from './parkingspaceempty/parkingspaceempty.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', component: ParkingspaceemptyComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParkingspaceemptyRoutingModule { }
