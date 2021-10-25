import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseUrl?: string = environment.apiServerUrl;

  constructor( private http: HttpClient) {}

  // Generic GET Method
  get<T = any>(path: string, options?: {}): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}${path}`, options);
  }


  // Generic PUT Method
  put<T>(path: string, body: object = {}): Observable<T> {
    return this.http.put<T>(
      `${this.baseUrl}${path}`,
      JSON.stringify(body));
  }

  // Generic POST Method
  post<T>(path: string, body?:{}, options?: {}): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}${path}`, body, options);
  }

  delete(path: string): Observable<any> {
    return this.http.delete(
      `${this.baseUrl}${path}`);
  }

}
