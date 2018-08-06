import {Component} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Random String Generator';

  randomStrings: any;

  constructor(private http: HttpClient){
  }

  ngOnInit(): void {
    this.http.get('/api/random').subscribe(data => {
      this.randomStrings = data;
    });
  }

  generateRandomString( parameters ): void {
    this.http.post('/api/random', parameters)
      .subscribe(data => {
          this.randomStrings = data;
        },
        err => {
          console.log(`Error occured ${err}`);
        }
      );
  }
}
