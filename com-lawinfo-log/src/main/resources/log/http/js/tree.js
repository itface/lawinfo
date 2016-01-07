$(document).ready(function () {

    // TREE
    //$('#myTree').on('loaded.fu.tree', function (e) {
    //    console.log('Loaded');
    //});

    function myTreeInit() {
        $('#myTree').tree({
            dataSource: function (options, callback) {
                var category = options == null ? "" : options.text;
                $.ajax({
                    type: 'POST',
                    url: "logInfoList.json",
                    data: {category: category},
                    dataType: "json",
                    success: function (list) {
                        setTimeout(function () {
                            var datas = [];
                            $.each(list, function (index, data) {
                                datas.push({
                                    text: data.name,
                                    type: data.type,
                                    attr: {id: data.name.replace(/\./g, "")}
                                });
                            });

                            callback({data: datas});

                            $.each(list, function (index, data) {
                                var level = data.level == "" ? "NULL" : data.level;
                                var button = $("#" + data.name.replace(/\./g, "")).find("button.level_info");
                                button.attr("data_log_name", data.name)
                                button.html(level);
                                button.on("click", function(evt){
                                    var name = $(evt.target).attr("data_log_name");
                                    $("#search_input").val(name);
                                    $("#search_btn").click();
                                    return false;
                                })
                            });
                        }, 400);
                    }
                });
            },
            multiSelect: false,
            cacheItems: false,
            folderSelect: false
        });
    }

    myTreeInit();


    $("select").on("click", function () {
        console.info(this)
        return false;
    });



    //$('#myTree').on('selected.fu.tree', function (e, selected) {
    //    console.log('Select Event: ', selected);
    //    console.log($('#myTree').tree('selectedItems'));
    //});

    //$('#myTree').on('updated.fu.tree', function (e, selected) {
    //    console.log('Updated Event: ', selected);
    //    console.log($('#myTree').tree('selectedItems'));
    //});

    //$('#myTree').on('opened.fu.tree', function (e, info) {
    //    console.log('Open Event: ', info);
    //});
    //
    //$('#myTree').on('closed.fu.tree', function (e, info) {
    //    console.log('Close Event: ', info);
    //});

    //$('#myTreeDefault').tree({
    //	dataSource: function(options, callback){
    //		setTimeout(function () {
    //			callback({ data: [
    //				{ name: 'Ascending and Descending', type: 'folder', dataAttributes: { id: 'folder1' } },
    //				{ name: 'Sky and Water I (with custom icon)', type: 'item', dataAttributes: { id: 'item1', 'data-icon': 'glyphicon glyphicon-file' } },
    //				{ name: 'Drawing Hands', type: 'folder', dataAttributes: { id: 'folder2', 'data-children': false } },
    //				{ name: 'Waterfall', type: 'item', dataAttributes: { id: 'item2' } },
    //				{ name: 'Belvedere', type: 'folder', dataAttributes: { id: 'folder3' } },
    //				{ name: 'Relativity (with custom icon)', type: 'item', dataAttributes: { id: 'item3', 'data-icon': 'glyphicon glyphicon-picture' } },
    //				{ name: 'House of Stairs', type: 'folder', dataAttributes: { id: 'folder4' } },
    //				{ name: 'Convex and Concave', type: 'item', dataAttributes: { id: 'item4' } }
    //			]});
    //		}, 400);
    //	}
    //});

    //$('#myTree').on('selected.fu.tree', function (e, info) {
    //    console.log('Select Event: ', info);
    //});


    //$('#myTreeDefault').on('selected.fu.tree', function (e, selected) {
    //	console.log('Select Event: ', selected);
    //	console.log($('#myTree').tree('selectedItems'));
    //});
    //
    //$('#myTreeDefault').on('updated.fu.tree', function (e, selected) {
    //	console.log('Updated Event: ', selected);
    //	console.log($('#myTree').tree('selectedItems'));
    //});
    //
    //$('#myTreeDefault').on('opened.fu.tree', function (e, info) {
    //	console.log('Open Event: ', info);
    //});
    //
    //$('#myTreeDefault').on('closed.fu.tree', function (e, info) {
    //	console.log('Close Event: ', info);
    //});

    //$('#btnTreeDestroy').click(function () {
    //	var markup = $('#myTree').tree('destroy');
    //	console.log( markup );
    //	$(this).closest('.section').append(markup);
    //	myTreeInit();
    //});
});

