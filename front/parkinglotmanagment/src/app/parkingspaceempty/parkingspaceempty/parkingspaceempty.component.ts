import { DataService } from './../../data.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ParkingSpace } from '../model/parkingspacemodel';



@Component({
  selector: 'app-parkingspaceempty',
  templateUrl: './parkingspaceempty.component.html',
  styleUrls: ['./parkingspaceempty.component.scss']
})
export class ParkingspaceemptyComponent implements OnInit {

  parkingspaceempty: Observable<ParkingSpace[]>

  constructor(private dataService: DataService) { 
    this.parkingspaceempty = this.dataService.list();
  }

  ngOnInit(): void {
  }


}
