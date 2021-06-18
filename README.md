# web_service_reset_password
yum install https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm   ==> tải mysql

amazon-linux-extras install epel -y   ==> installing epel -release

systemctl enable --now mysqld
systemctl status mysqld   ==> check status of mysqld.service
