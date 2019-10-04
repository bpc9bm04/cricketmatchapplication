import { Component, OnInit } from '@angular/core';

import { Match } from '../../match';
import { MatchService} from '../../match.service';

@Component({
  selector: 'match-favouritelist',
  templateUrl: './favouritelist.component.html',
  styleUrls: ['./favouritelist.component.css']
})
export class FavouritelistComponent implements OnInit {

  matches: Array<Match>;
  quickviewAPI: boolean;
  favouriteAPI: boolean;
  constructor(private matchService: MatchService) {
    console.log('inside constructor ....');
    this.matches = [];
    this.quickviewAPI = false;
    this.favouriteAPI = true;
   }

  ngOnInit() {
    
    this.getFavouritelist();

  }

  getFavouritelist(){
    this.matchService.getFavouritelistedMatches().subscribe((matches => {
      this.matches.push(...matches);
    }));
  }
}
