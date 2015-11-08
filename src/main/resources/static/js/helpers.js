/**
 * @author Connor
 * Created on 10/5/2015.
 */

function goBack() {
    if(confirm("Are you sure you want to navigate away from this page?"))
    {
        history.go(-1);
    }
    return false;
};