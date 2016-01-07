$(document).ready(function () {
    var columns = [
        {
            label: 'Name',
            property: 'name',
            sortable: true
        },
        {
            label: 'Level',
            property: 'level',
            sortable: false
        }
    ];

    var all_level = [];

    var all_items = [];

    var dataSource, filtering;

    function getSelect(item) {
        var name = item.name;
        var html_start = '<div class="input-group input-append dropdown combobox" data-log-name="' + name + '" data-initialize="combobox" style="width: 50%;"> <input type="text" class="form-control"> <div class="input-group-btn"> <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button> <ul class="dropdown-menu dropdown-menu-right"> ';
        $.each(all_level, function (index, data) {
            html_start += '<li data-value="' + data + '"><a href="#">' + data + '</a></li>'
        });
        var html_end = '</ul></div></div>';
        return html_start + html_end;
    }

    function initAllLevel() {
        $.ajax({
            type: 'GET',
            url: "allLevelList.json",
            dataType: "json",
            async: false,
            success: function (list) {
                all_level.push("NULL");
                $.each(list, function (index, data) {
                    all_level.push(data);
                });
            }
        });
    }

    function initAllLogInfo() {
        $.ajax({
            type: 'POST',
            url: "logInfoList.json",
            data: {category: "ALL"},
            dataType: "json",
            async: false,
            success: function (list) {
                all_items = $.extend([], list);
            }
        });
    }

    dataSource = function (options, callback) {
        var items = filtering(options);
        var resp = {
            count: items.length,
            items: [],
            page: options.pageIndex,
            pages: Math.ceil(items.length / (options.pageSize || 50))
        };
        var i, items, l;

        i = options.pageIndex * (options.pageSize || 50);
        l = i + (options.pageSize || 50);
        l = (l <= resp.count) ? l : resp.count;
        resp.start = i + 1;
        resp.end = l;

        resp.columns = columns;
        for (i; i < l; i++) {
            resp.items.push({name: items[i].name, level: getSelect(items[i])});
        }

        callback(resp);

        for (i = resp.start - 1; i < l; i++) {
            var item = items[i];
            var level = items[i].level == "" ? "NULL" : items[i].level;
            $(".combobox[data-log-name='" + item.name + "']").combobox('selectByValue', level);
        }

        $('.combobox').on('changed.fu.combobox', function (evt, data) {
            change_log_level($(evt.target).attr("data-log-name"), data.value)
        });

    };

    filtering = function (options) {
        var items = all_items;
        if (options.search) {
            var search = options.search.toLowerCase();
            items = _.filter(items, function (item) {
                return (item.name.toLowerCase().search(search) >= 0);
            });
        }
        if (options.sortProperty) {
            items = _.sortBy(items, function (item) {
                return item[options.sortProperty];
            });
            if (options.sortDirection === 'desc') {
                items.reverse();
            }
        }
        return items;
    };

    initAllLevel();

    initAllLogInfo();

    function change_log_level(name, level) {
        if ("NULL" == level) {
            return false;
        }
        $.ajax({
            type: 'POST',
            url: "setupLogger.json",
            data: {category: name, level: level},
            dataType: "text",
            async: false,
            success: function () {
                console.info("setupLogger", name, level);
                initAllLogInfo();
            }
        });
    }

    $('#myRepeater').repeater({
        dataSource: dataSource
    });
});

