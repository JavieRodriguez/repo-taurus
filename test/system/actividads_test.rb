require "application_system_test_case"

class ActividadsTest < ApplicationSystemTestCase
  setup do
    @actividad = actividads(:one)
  end

  test "visiting the index" do
    visit actividads_url
    assert_selector "h1", text: "Actividads"
  end

  test "creating a Actividad" do
    visit actividads_url
    click_on "New Actividad"

    fill_in "Descripcion", with: @actividad.descripcion
    fill_in "Etapa", with: @actividad.etapa_id
    click_on "Create Actividad"

    assert_text "Actividad was successfully created"
    click_on "Back"
  end

  test "updating a Actividad" do
    visit actividads_url
    click_on "Edit", match: :first

    fill_in "Descripcion", with: @actividad.descripcion
    fill_in "Etapa", with: @actividad.etapa_id
    click_on "Update Actividad"

    assert_text "Actividad was successfully updated"
    click_on "Back"
  end

  test "destroying a Actividad" do
    visit actividads_url
    page.accept_confirm do
      click_on "Destroy", match: :first
    end

    assert_text "Actividad was successfully destroyed"
  end
end