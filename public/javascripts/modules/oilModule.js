(function(Oil) {

    Oil.Model = Backbone.Model.extend({

    });

    Oil.Collection = Backbone.Collection.extend({
        url: '/products',

        model: Oil.Model
    });

    Oil.Router = Backbone.Router.extend({
        routes: {
            "": "menu",
            "products": "listProducts",
            "product/:productId": "productDetail"
        },

        menu: function() {
            console.log('menu');
        },

        listProducts: function() {
            console.log('list of products');
            this.collection = new Oil.Collection();
            this.view = new Oil.ProductsView({model: this.collection});
            this.view.render();
        },

        productDetail: function(id) {
            console.log('id='+id);
        }
    });

    Oil.ProductsView = Backbone.View.extend({

        initialize: function() {
            this.model.bind("change", this.render, this);
        },

        render: function() {
            console.log('render products list');
        }
    })

})(oil.module("Oil"));