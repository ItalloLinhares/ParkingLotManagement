import { DataService } from './../../data.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { ParkingSpace } from '../model/parkingspacemodel';
import { MatTableModule } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import {NgxPaginationModule} from 'ngx-pagination';


@Component({
  selector: 'app-parkingspaceempty',
  templateUrl: './parkingspaceempty.component.html',
  styleUrls: ['./parkingspaceempty.component.scss']
})
export class ParkingspaceemptyComponent implements OnInit {

  parkingspaceempty: Observable<ParkingSpace[]>;

  displayedColumns = ['id'];
  constructor(private dataService: DataService) { 
    this.parkingspaceempty = this.dataService.list();
  }

  ngOnInit(): void {
  }
}