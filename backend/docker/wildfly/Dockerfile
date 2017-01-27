FROM jboss/wildfly
RUN /opt/jboss/wildfly/bin/add-user.sh jbadmin jbadmin123 --silent
CMD /opt/jboss/wildfly/bin/standalone.sh --debug -b=0.0.0.0 -bmanagement=0.0.0.0
EXPOSE 8080 8443 8787 9990

