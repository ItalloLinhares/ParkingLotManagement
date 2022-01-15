import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ParkingSpace } from './parkingspaceempty/model/parkingspacemodel';


@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private httpClient: HttpClient) { }

  private listParkingSpaceAvailable = '/api/listParkingSpaceAvailable';


  public list(){
    console.log(this.httpClient.get(this.listParkingSpaceAvailable));
    return this.httpClient.get<ParkingSpace[]>(this.listParkingSpaceAvailable)
  }
}
