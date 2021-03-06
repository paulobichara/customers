define({ "api": [
  {
    "type": "put",
    "url": "/users/",
    "title": "Add a new company user",
    "name": "AddCompanyUser",
    "group": "CompanyUser",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "firstName",
            "description": "<p>[QUERY PARAMETER] company user first name</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "lastName",
            "description": "<p>[QUERY PARAMETER] company user last name</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "role",
            "description": "<p>[QUERY PARAMETER] company user role in company</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "email",
            "description": "<p>[QUERY PARAMETER] company user email</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "companyId",
            "description": "<p>[QUERY PARAMETER] Customer company id that the user works for</p> "
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "id",
            "description": "<p>company user unique identifier</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "firstName",
            "description": "<p>company user first name</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "lastName",
            "description": "<p>company user last name</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "role",
            "description": "<p>company user role in company</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "email",
            "description": "<p>company user email</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>Object</p> ",
            "optional": false,
            "field": "company",
            "description": "<p>company that the user works for</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CompanyUsersServicesException",
            "description": "<p>Some error occurred in CompanyUsersServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CompanyUsersServices.java",
    "groupTitle": "CompanyUser"
  },
  {
    "type": "get",
    "url": "/users/",
    "title": "Get all company users",
    "name": "GetAllCompanyUsers",
    "group": "CompanyUser",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Object[]</p> ",
            "optional": false,
            "field": "data",
            "description": "<p>List of company user objects</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CompanyUsersServicesException",
            "description": "<p>Some error occurred in CompanyUsersServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CompanyUsersServices.java",
    "groupTitle": "CompanyUser"
  },
  {
    "type": "get",
    "url": "/users/:companyUserId",
    "title": "Get a specific company user",
    "name": "GetCompanyUser",
    "group": "CompanyUser",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "companyUserId",
            "description": "<p>Company user unique identifier</p> "
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "id",
            "description": "<p>company user unique identifier</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "firstName",
            "description": "<p>company user first name</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "lastName",
            "description": "<p>company user last name</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "role",
            "description": "<p>company user role in company</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "email",
            "description": "<p>company user email</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>Object</p> ",
            "optional": false,
            "field": "company",
            "description": "<p>company that the user works for</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CompanyUsersServicesException",
            "description": "<p>Some error occurred in CompanyUsersServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CompanyUsersServices.java",
    "groupTitle": "CompanyUser"
  },
  {
    "type": "get",
    "url": "/users/",
    "title": "Get similar company users",
    "name": "GetSimilarCompanyUsers",
    "group": "CompanyUser",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Object[]</p> ",
            "optional": false,
            "field": "data",
            "description": "<p>List of similar company user objects</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CompanyUsersServicesException",
            "description": "<p>Some error occurred in CompanyUsersServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CompanyUsersServices.java",
    "groupTitle": "CompanyUser"
  },
  {
    "type": "delete",
    "url": "/users/:companyUserId",
    "title": "Remove a specific company user",
    "name": "RemoveCompanyUser",
    "group": "CompanyUser",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "companyUserId",
            "description": "<p>Company user unique identifier</p> "
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "message",
            "description": "<p><code>User removed successfully</code></p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CompanyUsersServicesException",
            "description": "<p>Some error occurred in CompanyUsersServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CompanyUsersServices.java",
    "groupTitle": "CompanyUser"
  },
  {
    "type": "put",
    "url": "/users/:companyUserId",
    "title": "Update a specific company user",
    "name": "UpdateCompanyUser",
    "group": "CompanyUser",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "companyUserId",
            "description": "<p>Company user unique identifier</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "firstName",
            "description": "<p>[QUERY PARAMETER] company user first name</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "lastName",
            "description": "<p>[QUERY PARAMETER] company user last name</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "role",
            "description": "<p>[QUERY PARAMETER] company user role in company</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "email",
            "description": "<p>[QUERY PARAMETER] company user email</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "companyId",
            "description": "<p>[QUERY PARAMETER] Customer company id that the user works for</p> "
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "id",
            "description": "<p>company user unique identifier</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "firstName",
            "description": "<p>company user first name</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "lastName",
            "description": "<p>company user last name</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "role",
            "description": "<p>company user role in company</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "email",
            "description": "<p>company user email</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>Object</p> ",
            "optional": false,
            "field": "company",
            "description": "<p>company that the user works for</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CompanyUsersServicesException",
            "description": "<p>Some error occurred in CompanyUsersServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CompanyUsersServices.java",
    "groupTitle": "CompanyUser"
  },
  {
    "type": "put",
    "url": "/companies/",
    "title": "Add a new customer company",
    "name": "AddCustomerCompany",
    "group": "CustomerCompany",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "name",
            "description": "<p>[QUERY PARAMETER] Customer company name</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "description",
            "description": "<p>[QUERY PARAMETER] Customer company description</p> "
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "id",
            "description": "<p>customer company unique identifier</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "name",
            "description": "<p>customer company name</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "description",
            "description": "<p>customer company description</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CustomerCompaniesServicesException",
            "description": "<p>Some error occurred in CustomerCompaniesServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CustomerCompaniesServices.java",
    "groupTitle": "CustomerCompany"
  },
  {
    "type": "get",
    "url": "/companies/",
    "title": "Get all customer companies",
    "name": "GetAllCustomerCompanies",
    "group": "CustomerCompany",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Object[]</p> ",
            "optional": false,
            "field": "data",
            "description": "<p>List of customer companies objects</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CustomerCompaniesServicesException",
            "description": "<p>Some error occurred in CustomerCompaniesServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CustomerCompaniesServices.java",
    "groupTitle": "CustomerCompany"
  },
  {
    "type": "get",
    "url": "/companies/:companyId",
    "title": "Get a specific customer company",
    "name": "GetCustomerCompany",
    "group": "CustomerCompany",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "companyId",
            "description": "<p>Customer company unique identifier</p> "
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "id",
            "description": "<p>customer company unique identifier</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "name",
            "description": "<p>customer company name</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "description",
            "description": "<p>customer company description</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CustomerCompaniesServicesException",
            "description": "<p>Some error occurred in CustomerCompaniesServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CustomerCompaniesServices.java",
    "groupTitle": "CustomerCompany"
  },
  {
    "type": "delete",
    "url": "/companies/:companyId",
    "title": "Remove a specific customer company",
    "name": "RemoveCustomerCompany",
    "group": "CustomerCompany",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "companyId",
            "description": "<p>Customer company unique identifier</p> "
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "message",
            "description": "<p><code>Company removed successfully</code></p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CustomerCompaniesServicesException",
            "description": "<p>Some error occurred in CustomerCompaniesServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CustomerCompaniesServices.java",
    "groupTitle": "CustomerCompany"
  },
  {
    "type": "put",
    "url": "/companies/:companyId",
    "title": "Update a specific customer company",
    "name": "UpdateCustomerCompany",
    "group": "CustomerCompany",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "companyId",
            "description": "<p>Customer company unique identifier</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "name",
            "description": "<p>[QUERY PARAMETER] Customer company name</p> "
          },
          {
            "group": "Parameter",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "description",
            "description": "<p>[QUERY PARAMETER] Customer company description</p> "
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "<p>Number</p> ",
            "optional": false,
            "field": "id",
            "description": "<p>customer company unique identifier</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "name",
            "description": "<p>customer company name</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "description",
            "description": "<p>customer company description</p> "
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "CustomerCompaniesServicesException",
            "description": "<p>Some error occurred in CustomerCompaniesServices - <code>[error-message]</code></p> "
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "customers-service/src/main/java/org/paulobichara/customers/service/CustomerCompaniesServices.java",
    "groupTitle": "CustomerCompany"
  },
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p> "
          },
          {
            "group": "Success 200",
            "type": "<p>String</p> ",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p> "
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "customers-service/target/customers-service-1.0.0-SNAPSHOT/docs/main.js",
    "group": "_home_monk_Develop_workspace_helpscout_customers_customers_service_target_customers_service_1_0_0_SNAPSHOT_docs_main_js",
    "groupTitle": "_home_monk_Develop_workspace_helpscout_customers_customers_service_target_customers_service_1_0_0_SNAPSHOT_docs_main_js",
    "name": ""
  }
] });