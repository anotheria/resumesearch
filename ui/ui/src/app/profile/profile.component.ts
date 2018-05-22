import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Profile} from "../Profile";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  toSearch: string;
  profiles: Profile[];
  total : number;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }

  call(toSearch: string): void {
    let a = this.prepareWordsArray(toSearch);
    let query = this.buildLink(a);
    let url = "http://localhost:8983/solr/index/select?q=" + query + "&rows=3";
    //console.log(url);
    this.http.get(url).subscribe(data => {

      let d = data['response'].docs;
      //console.log(data['response'].docs);
      this.profiles = new Array();
      for (let o of d) {
        //console.log(o.firstName[0])
        let p = new Profile();
        p.name = o.firstName[0] + " " + o.lastName[0];
        p.skills = o.skills;
       // console.log(p);
        this.profiles.push(p);
      }
      this.total = data['response'].numFound;
    });

  }

  private removeEmpty(array: string[]) {
    let n = new Array();
    for (let w of array) {
      if (w !== "") {
        n.push(w);
      }
    }
    return n;
  }

  private prepareWordsArray(str: string) {
    let spaceSplit = str.split(" ");
    let a = new Array();
    for (let word of spaceSplit) {
      a.push(word.split(","));
    }
    let fin = new Array();
    for (let ar of a) {
      fin = fin.concat(this.removeEmpty(ar));
    }
    return fin;
  }

  private buildLink(words: string[]) {
    //skills:(*java*%20AND%20*mongo*%20AND%20*ant*%20AND%20*php*%20AND%20*EJB*)
    let query = "skills:(";
    let k = false;
    for (let word of words) {
      if (k) {
        query += " AND ";
      }
      // *param* ?
      query += "" + word + "";
      k = true;
    }
    query += ")";
    //console.log(query);
    return query;
  }

}
