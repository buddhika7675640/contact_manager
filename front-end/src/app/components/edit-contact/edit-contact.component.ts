import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {ContactService} from "../../services/contact.service";

@Component({
  selector: 'app-edit-contact',
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./edit-contact.component.css']
})
export class EditContactComponent implements OnInit {

  id:any;
  user:User = new User();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private contactService: ContactService) {

  }

  ngOnInit(): void {

    this.route.paramMap.subscribe((param) => {
      this.id = param.get(`id`);
    });

    this.contactService.findOne(this.id).subscribe((data)=>{
      this.user = data;}, error => console.log(error));

  }

/*
  submitUpdate(){
    //this.router.navigate(['/']).then();
    console.log("User Data ...",this.user)
    console.log("second id ... ",this.id)

    this.contactService.updateContact(this.id , this.user).subscribe((data)=>{
      this.router.navigate(['/']).then();

      console.log(data)
    }, error => console.log("Not worked ",error));






  }
  gotoUserList() {

  }


 */

  public submitUpdate() {
    //this.loading = true;
    this.contactService.updateContact(this.id,this.user).subscribe((data) => {
      //this.loading = false;
      this.router.navigate(['/']).then();
    }, (error) => {
      console.log(error)
    })
  }
}
