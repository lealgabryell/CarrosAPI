import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Carro } from '../models/carro';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarroService {

  http = inject(HttpClient);

  API = "localhost:8080/api/carro";

  constructor() { }

  findAll(): Observable<Carro[]> {
    return this.http.get<Carro[]>(this.API + "/findAll")
  }

}
