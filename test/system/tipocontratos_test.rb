require "application_system_test_case"

class TipocontratosTest < ApplicationSystemTestCase
  setup do
    @tipocontrato = tipocontratos(:one)
  end

  test "visiting the index" do
    visit tipocontratos_url
    assert_selector "h1", text: "Tipocontratos"
  end

  test "creating a Tipocontrato" do
    visit tipocontratos_url
    click_on "New Tipocontrato"

    fill_in "Tipo", with: @tipocontrato.tipo
    click_on "Create Tipocontrato"

    assert_text "Tipocontrato was successfully created"
    click_on "Back"
  end

  test "updating a Tipocontrato" do
    visit tipocontratos_url
    click_on "Edit", match: :first

    fill_in "Tipo", with: @tipocontrato.tipo
    click_on "Update Tipocontrato"

    assert_text "Tipocontrato was successfully updated"
    click_on "Back"
  end

  test "destroying a Tipocontrato" do
    visit tipocontratos_url
    page.accept_confirm do
      click_on "Destroy", match: :first
    end

    assert_text "Tipocontrato was successfully destroyed"
  end
end
