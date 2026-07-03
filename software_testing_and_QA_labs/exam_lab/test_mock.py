from unittest.mock import Mock

def register_user(phone, sms_services):
    result = sms_services.send(
        phone,
        "Welcome"
    )    
    return result

def test_register_user():
    sms = Mock()
    sms.send.return_value = True

    result = register_user("123-4567", sms)

    assert result == True
    
    sms.send.assert_called_once_with(
    "123-4567",
    "Welcome"
)

