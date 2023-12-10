import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {ContactService} from "../../services/contact.service";
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-contact-manager',
  templateUrl: './contact-manager.component.html',
  styleUrls: ['./contact-manager.component.css']
})
export class ContactManagerComponent implements OnInit {


  loading:boolean =false;
  public user: User[] = [];
  errorMessage:string|null=null;

  constructor(private contactService: ContactService) {
  }
  ngOnInit(): void {
    this.getAllContactsFormServer();
  }

  public getAllContactsFormServer(){

    this.loading = true;
    this.contactService.findAll().subscribe(data => {
      this.user = data;
      this.loading = false;
    },(error) =>{
      this.errorMessage = error;
      this.loading = false;
    });
  }

  public clickDeleteContact(id : any){
    if(id){
      this.contactService.deleteContact(id).subscribe(()=>{

         this.getAllContactsFormServer();

      })
    }
  }

}
