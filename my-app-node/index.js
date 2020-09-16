const hbase = require('hbase');
const client = hbase({host: 'hbase', port: 8080});
client
    .table('user')
    .row('mu-user')
    .get('contact', function (err, cf) {
        console.log('!Error: ', err);
        console.log('!ColumnFamily: ', cf);
    });
