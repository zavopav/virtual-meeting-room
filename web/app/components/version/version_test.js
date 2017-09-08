(function () {
    'use strict';

    describe('vmrApp.version module', function () {
        beforeEach(module('vmrApp.version'));

        describe('version service', function () {
            it('should return current version', inject(function (version) {
                expect(version).toEqual('0.1');
            }));
        });
    });
}());