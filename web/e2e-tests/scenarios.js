(function () {
    'use strict';

    /* https://github.com/angular/protractor/blob/master/docs/toc.md */

    describe('wbd app', function () {


        it('should automatically redirect to /welcome when location hash/fragment is empty', function () {
            browser.get('index.html');
            expect(browser.getLocationAbsUrl()).toMatch("/welcome");
        });


        describe('chat', function () {

            beforeEach(function () {
                browser.get('index.html#!/chat/1');
            });


            it('should render chat when user navigates to /chat', function () {
                expect(element.all(by.css('[ng-view] p')).first().getText()).toMatch(/partial for view 1/);
            });

        });


        describe('welcome', function () {

            beforeEach(function () {
                browser.get('index.html#!/welcome');
            });


            it('should render view2 when user navigates to /welcome', function () {
                expect(element.all(by.css('[ng-view] p')).first().getText()).toMatch(/Welcome!/);
            });

        });
    });
}());