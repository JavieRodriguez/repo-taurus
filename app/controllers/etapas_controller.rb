class EtapasController < ApplicationController
  before_action :set_etapa, only: [:show, :edit, :update, :destroy]
  before_action :set_modalidad
  before_action :set_proceso

  # GET /etapas
  # GET /etapas.json
  def index
    @etapas = Etapa.all
  end

  # GET /etapas/1
  # GET /etapas/1.json
  def show
    @actividades=Actividad.where('etapa_id=?',params[:id])
  end

  # GET /etapas/new
  def new
    @etapa = Etapa.new
  end

  # GET /etapas/1/edit
  def edit
  end

  # POST /etapas
  # POST /etapas.json
  def create
    @etapa = Etapa.new(etapa_params)

    respond_to do |format|
      if @etapa.save
        format.html { redirect_to @etapa, notice: 'Etapa was successfully created.' }
        format.json { render :show, status: :created, location: @etapa }
      else
        format.html { render :new }
        format.json { render json: @etapa.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /etapas/1
  # PATCH/PUT /etapas/1.json
  def update
    respond_to do |format|
      if @etapa.update(etapa_params)
        format.html { redirect_to @etapa, notice: 'Etapa was successfully updated.' }
        format.json { render :show, status: :ok, location: @etapa }
      else
        format.html { render :edit }
        format.json { render json: @etapa.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /etapas/1
  # DELETE /etapas/1.json
  def destroy
    @etapa.destroy
    respond_to do |format|
      format.html { redirect_to etapas_url, notice: 'Etapa was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_etapa
      @etapa = Etapa.find(params[:id])
    end
    def set_proceso
      @proceso = Proceso.find(params[:proceso_id])
    end
    def set_modalidad
      @modalidad = Modalidad.find(params[:modalidad_id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def etapa_params
      params.require(:etapa).permit(:nombre, :modalidad_id)
    end
end
