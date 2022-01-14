import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ParkingSpace } from './parkingspaceempty/model/parkingspacemodel';


@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private httpClient: HttpClient) { }

  private listParkingSpaceAvailable = 'http://localhost:8080/management/listParkingSpaceAvailable';


  public list(){
    
    this.httpClient.get<ParkingSpace[]>(this.listParkingSpaceAvailable).subscribe(
      response => {
        console.log(response);
      }
    );
    return this.httpClient.get<ParkingSpace[]>(this.listParkingSpaceAvailable)
  }
}
