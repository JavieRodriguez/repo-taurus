class ProcesosController < ApplicationController
  before_action :set_proceso, only: [:show, :edit, :update, :destroy]
  before_action :set_modalidad

  # GET /procesos
  # GET /procesos.json
  def index
    @procesos = Proceso.where("modalidad_id = ?", params[:modalidad_id])
  end

  # GET /procesos/1
  # GET /procesos/1.json
  def show
    @etapas=Etapa.all
    @tipocontratos = Tipocontrato.all
  end

  # GET /procesos/new
  def new
    @proceso = Proceso.new
    @tipocontratos = Tipocontrato.all
  end

  # GET /procesos/1/edit
  def edit
    @tipocontratos = Tipocontrato.all
  end

  # POST /procesos
  # POST /procesos.json
  def create
    @proceso = Proceso.new(proceso_params)
    @proceso.user = current_user
    @proceso.modalidad = @modalidad
    @tipocontrato = Tipocontrato.find(proceso_params[:tipocontrato_id])
    @proceso.tipocontrato = @tipocontrato


    respond_to do |format|
      if @proceso.save
        format.html { redirect_to [@modalidad, @proceso], notice: 'Proceso fue correctamente creado.' }
        format.json { render :show, status: :created, location: [@modalidad, @proceso] }
      else
        format.html { render :new }
        format.json { render json: @proceso.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /procesos/1
  # PATCH/PUT /procesos/1.json
  def update
    respond_to do |format|
      if @proceso.update(proceso_params)
        format.html { redirect_to [@modalidad, @proceso], notice: 'El proceso fue correctamente actualizado.' }
        format.json { render :show, status: :ok, location: @proceso }
      else
        format.html { render :edit }
        format.json { render json: @proceso.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /procesos/1
  # DELETE /procesos/1.json
  def destroy
    @proceso.destroy
    respond_to do |format|
      format.html { redirect_to modalidad_procesos_url, notice: 'Proceso was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_proceso
      @proceso = Proceso.find(params[:id])
    end

    def set_modalidad
      @modalidad = Modalidad.find(params[:modalidad_id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def proceso_params
      params.require(:proceso).permit(:numero, :palabraclave, :objeto, :presupuesto, :unidadplazo, :plazo, :tipocontrato_id, :modalidad_id)
    end
end
