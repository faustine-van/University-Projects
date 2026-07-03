import pytest

@pytest.fixture
def user():
    return 1

@pytest.fixture
def role():
    return "admin"


@pytest.fixture
def sample_data(user, role):
    return {
        "user_id": user,
        "role": role
    }
def test_sample(sample_data):
    assert sample_data["user_id"] == 1
    assert sample_data["role"] == "admin"

