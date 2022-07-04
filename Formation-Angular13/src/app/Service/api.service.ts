import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpHeaders,
} from '@angular/common/http';


import { Observable } from 'rxjs';
const TOKEN_KEY = 'token';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private host = 'http://localhost:8090';


  constructor(private http: HttpClient) { }



apiPost(endpoint: string, body: any) {
  return this.http.post(this.host + endpoint, body);
}
apiGetAll(endpoint: string) {
  return this.http.get(this.host + endpoint);
}
apiPut(endpoint: string, body: any) {
  return this.http.put(this.host + endpoint, body);
}
apiDelete(endpoint: string) {
  return this.http.delete(this.host + endpoint);
}


}
