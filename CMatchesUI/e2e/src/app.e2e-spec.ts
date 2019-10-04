import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';

describe('Match UI App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('CMatchesUI');
  });

  it('should be redirected to /login route', () => {
   
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.id('firstName')).sendKeys('super');
    browser.element(by.id('lastName')).sendKeys('super');
    browser.element(by.id('userId')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should able to login and navigate to all matches screen', () => {
    browser.element(by.id('userId')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/matches/all');
  });

  

  it('should able to add matches to favouritelist', () => {
    browser.driver.manage().window().maximize();
    browser.sleep(1000);
    

    const searchItems = element.all(by.css('.match-thumbnail'));
    expect(searchItems.count()).toBe(93);
    searchItems.get(0).click();
    browser.element(by.css('.addButton')).click();

  });

});
