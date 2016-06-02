package br.com.easyvansapp.easyvans;

/**
 * Created by Belal on 10/24/2015.
 */
public class ConfigUser {

    //Address of our scripts of the CRUD
    public static final String URL_ADD_USERS="http://www.schooltransapp.com.br/restservices/addUser.php";
    public static final String URL_GET_ALL = "http://pluve.com.br/test/getAllEmp.php";
    public static final String URL_GET_EMP = "http://pluve.com.br/test/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://pluve.com.br/test/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://pluve.com.br/test/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_ID_USER = "id_user";
    public static final String KEY_USER_NAME = "usr_name";
    public static final String KEY_USER_CPF = "usr_cpf";
    public static final String KEY_USER_TELEPHONE = "usr_telephone";
    public static final String KEY_USER_PASSWORD = "usr_password";
    public static final String KEY_USER_URL_IMAGE = "usr_url_image";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID_USER = "id_user";
    public static final String TAG_USER_NAME = "usr_name";
    public static final String TAG_USER_CPF = "usr_cpf";
    public static final String TAG_USER_TELEPHONE = "usr_telephone";
    public static final String TAG_USER_PASSWORD = "usr_password";
    public static final String TAG_USER_URL_IMAGE = "usr_url_image";

    //employee id to pass with intent
    public static final String USER_ID = "user_id";
}
