import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'parkingspaceempty'},
  {
    path: 'parkingspaceempty',
    loadChildren: () => import('./parkingspaceempty/parkingspaceempty.module').then(m => m.ParkingspaceemptyModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
