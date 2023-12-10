import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {ContactService} from "../../services/contact.service";

@Component({
  selector: 'app-view-contact',
  templateUrl: './view-contact.component.html',
  styleUrls: ['./view-contact.component.css']
})
export class ViewContactComponent implements OnInit {

  public loading: boolean = false;
  public id: any;
  //user:User;
  // user:User = new User();


  public user: User = {} as User;
  public errorMessage: string | null = null;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private contactService: ContactService) {
  }

  ngOnInit(): void {
    /*
        this.id = this.route.snapshot.paramMap.get(`id`);


            this.contactService.findOne(this.id).subscribe(data => {
              this.user = data;
            });
          }





        //this.user = new User();
        this.contactService.findOne(this.id).subscribe((data) => {
          this.user = data;
        });

       */


        this.route.paramMap.subscribe((param) => {
          this.id = param.get(`id`);
        });



          if(this.id){
            this.loading = true;
            this.contactService.findOne(this.id).subscribe((data)=>{
              this.user = data;
              this.loading = false;
            }, (error)=>{
              this.errorMessage = error;
              this.loading=false;
            });
          }
        }


          public isNotEmpty(){
          return Object.keys(this.user).length>0;
          }




  }

