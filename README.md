# hbase-sandbox
docker ps
docker exec -i -t 7d1c1f19c186 /bin/bash # hbase
hbase shell
create 'user', {NAME=>'contact', version => 3}, {NAME=>'account'}
put 'user','mu-user','contact:name','ihor'
scan 'user'
get 'user','mu-user'

