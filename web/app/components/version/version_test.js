(function () {
    'use strict';

    describe('wbdApp.version module', function () {
        beforeEach(module('wbdApp.version'));

        describe('version service', function () {
            it('should return current version', inject(function (version) {
                expect(version).toEqual('0.1');
            }));
        });
    });
}());