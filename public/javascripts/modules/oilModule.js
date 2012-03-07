(function(Oil) {

    Oil.Model = Backbone.Model.extend({

    });

    Oil.Collection = Backbone.Collection.extend({
        url: '/products',

        model: Oil.Model
    });

    Oil.Router = Backbone.Router.extend({

        initialize: function() {
            this.collection = new Oil.Collection();
        },

        routes: {
            "": "listProducts",
            "productlist": "listProducts",
            "product/:productId": "productDetail"
        },

        menu: function() {
            console.log('menu');
        },

        listProducts: function() {
            console.log('list of products');

            this.view = new Oil.ProductsView({model: this.collection});
            $('#content').html(this.view.render().el);
        },

        productDetail: function(id) {
            var model = this.collection.get(id);
            this.view = new Oil.ProductView({model: model});
            this.view.render();
        }
    });

    Oil.ProductsView = Backbone.View.extend({

        initialize: function() {
            this.model.bind("change", this.render, this);
        },

        render: function() {
            console.log('render products list');
            $(this.el).html('products');
            return this;
        }
    });

    Oil.ProductView = Backbone.View.extend({
        render: function() {
            console.log('render product details for ' + this.model.id);
            return this;
        }
    });

})(oil.module("Oil"));