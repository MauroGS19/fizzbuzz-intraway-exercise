import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {Operation} from "../models/operation.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FizzbuzzService {
  private fizzBuzzUrl = 'fizzbuzz';

  constructor( private http: HttpClient) { }

  getOperations(first: number, second: number): Observable<Operation> {
    return this.http.get<Operation>(`${environment.api_url}${this.fizzBuzzUrl}/${first}/${second}`);
  }

}
