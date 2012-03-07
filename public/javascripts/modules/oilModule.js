(function(Oil) {

    Oil.Model = Backbone.Model.extend({

    });

    Oil.Collection = Backbone.Collection.extend({
        url: '/products',

        model: Oil.Model,

        parse: function(response) {
            return response.products;
        }
    });

    Oil.Router = Backbone.Router.extend({

        initialize: function() {
            this.collection = new Oil.Collection();
            this.collection.fetch();            
        },

        routes: {
            "": "menu",
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
            $('#content').html(this.view.render().el);
        }
    });

    Oil.ProductsView = Backbone.View.extend({

        initialize: function() {
            this.model.bind("reset", this.render, this);
        },

        render: function() {
            console.log('render products list');
            var names = _.map(this.models, function(m) {
                return m.name;
            });

            var data = {
                "data" : names
            };

            var t = '<p>Products:</p><br/><ul>{{#data}<li>{{name}}</li>{{/data}}</ul>';

            $(this.el).html(Mustache.render(t, data));
            return this;
        }
    });

    Oil.ProductView = Backbone.View.extend({
        render: function() {
            $(this.el).html('product detail');
            console.log('render product details for ');
            return this;
        }
    });

})(oil.module("Oil"));