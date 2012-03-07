var oil = {

    module: function() {

        // internal module cache.
        var modules = {};

        // Create a new module reference scaffold or load an existing module.
        return function(name) {
            // If this module has already been created, return it.
            if (modules[name]) {
                return modules[name];
            }
            // Create a module and save it under this name
            return modules[name] = { Views: {} };
        };
    }(),

    init: function() {
        var oilModule = this.module('Oil');
        new oilModule.Router();
        Backbone.history.start();
    }
};