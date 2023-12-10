import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {User} from "../model/user";


@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private usersUrl: string;

  constructor(private httpclient:HttpClient) {
    this.usersUrl = `http://localhost:8080/user`;
  }

  //getAll Contacts
  public findAll(): Observable<User[]> {
    return this.httpclient.get<User[]>(this.usersUrl);
  }

  //save Contact
  public save(user: User):Observable<User> {
    console.log('Save ----- ',user)
    return this.httpclient.post<User>(this.usersUrl, user);
  }

  //getOne Contact
 public findOne(id:any): Observable<User>{
       return this.httpclient.get<User>(`${this.usersUrl}/${id}`);
  }

//update contact

  public updateContact(id: any, user:User):Observable<User>{

    let dataUrl: string = `${this.usersUrl}/${id}`
    return this.httpclient.put<User>(dataUrl,user).pipe(catchError(this.handleError));

    //return this.httpclient.put<User>(`${this.usersUrl}/${id}`, user);
  }




//delete contact
  public deleteContact(id: any): Observable<User>{
   // return this.httpclient.delete(`${this.usersUrl}/${id}`);
    return this.httpclient.delete<User>(`${this.usersUrl}/${id}`).pipe(catchError(this.handleError));
  }





  // public findOne(id: number): Observable<User>{
   // let dataUrl:string =`${this.usersUrl}/${id}`;
   // return this.httpclient.get<User>(dataUrl);
 // }





  //error Handling
  public handleError(error:HttpErrorResponse){
    let errorMessage:string = '';
    if (error.error instanceof ErrorEvent){
      //client error
      errorMessage = `Error : ${error.error.message}`
    }
    else{
      //server error
      errorMessage = `Status : ${error.status}\n Message:${error.error.message}`;
    }
    return throwError(errorMessage);
  }

}

