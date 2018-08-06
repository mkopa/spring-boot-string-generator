import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Password Generator';

  randomStrings: any;

  constructor(private http: HttpClient, private snackBar: MatSnackBar){
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

  copyText(textToBeCopied) {
    let textarea = null;
    textarea = window.document.createElement("textarea");
    textarea.style.height = "0px";
    textarea.style.left = "-100px";
    textarea.style.opacity = "0";
    textarea.style.position = "fixed";
    textarea.style.top = "-100px";
    textarea.style.width = "0px";
    document.body.appendChild(textarea);
    textarea.value = textToBeCopied;
    textarea.select();
    let successful = document.execCommand("copy");
    if (successful) {
      this.snackBar.openFromComponent(CopiedComponent, {
        duration: 500,
      });
    }
    if (textarea && textarea.parentNode) {
      textarea.parentNode.removeChild(textarea);
    }
  }
}

@Component({
  selector: 'copied-snack',
  template: `
    <span class="copied-snack-bar">
        Copied
    </span>
  `,
  styles: [`
    .copied-snack-bar {
      color: hotpink;
    }
  `],
})
export class CopiedComponent {}
