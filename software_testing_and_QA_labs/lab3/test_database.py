import pytest

@pytest.fixture
def database():
    print("connect")

    yield "db-connection"

    print("disconnect")

def test_database(database):
    print(f"Using {database}")
    assert database == "db-connection"