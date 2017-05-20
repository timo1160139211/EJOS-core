# EJOS-JudgeOnlineSystem - test
实验在线评估系统：
    大学生实验/课程设计 在线评估。  

## 环境
  wildfly10 + jsf + JPA + CDI + github + maven + eclipse

## 如何运行

#### 一：
  需要首先在wildfly中建立mysql数据源，参考：
       http://dz.sdut.edu.cn/blog/subaochen/2016/11/wildfly%E9%85%8D%E7%BD%AEpostgresql%E6%95%B0%E6%8D%AE%E6%BA%90/
          &&
       http://blog.csdn.net/timo1160139211/article/details/70490210

#### 二：
start wildfly server(in wildfly/):
  ./standalone.sh
open mysql service:
  service mysql start
finally tpye command in console：
  mvn clean package wildfly:deploy

## 如何参与

PR



