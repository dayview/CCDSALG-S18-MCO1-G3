import plotly.graph_objects as go
import plotly.io as pio
import json

datasets = ['random100.txt', 'random25000.txt', 'random50000.txt', 
            'random75000.txt', 'random100000.txt', 'almostsorted.txt', 
            'totallyreversed.txt']

insertion_sort = [0.24, 1172.64, 10752.48, 28587.24, 57103.20, 8586.48, 119193.48]
selection_sort = [0.36, 847.44, 14342.16, 35691.16, 69074.44, 68322.64, 59373.96]
merge_sort = [0.20, 7.44, 18.20, 35.48, 41.60, 28.50, 15.36]
radix_sort = [0.08, 8.92, 23.32, 57.76, 44.56, 57.16, 35.65]

random_sizes = [100, 25000, 50000, 75000, 100000]
random_insertion = [0.24, 1172.64, 10752.48, 28587.24, 57103.20]
random_selection = [0.36, 847.44, 14342.16, 35691.16, 69074.44]
random_merge = [0.20, 7.44, 18.20, 35.48, 41.60]
random_radix = [0.08, 8.92, 23.32, 57.76, 44.56]

fig1 = go.Figure()
fig1.add_trace(go.Scatter(
    x=random_sizes, 
    y=random_insertion, 
    mode='lines+markers', 
    name='Insertion Sort',
    line=dict(width=3),
    marker=dict(size=8)
))
fig1.add_trace(go.Scatter(
    x=random_sizes, 
    y=random_selection, 
    mode='lines+markers', 
    name='Selection Sort',
    line=dict(width=3),
    marker=dict(size=8)
))
fig1.add_trace(go.Scatter(
    x=random_sizes, 
    y=random_merge, 
    mode='lines+markers', 
    name='Merge Sort',
    line=dict(width=3),
    marker=dict(size=8)
))
fig1.add_trace(go.Scatter(
    x=random_sizes, 
    y=random_radix, 
    mode='lines+markers', 
    name='Radix Sort',
    line=dict(width=3),
    marker=dict(size=8)
))

fig1.update_layout(
    title="Execution Time vs Dataset Size (Random Data)",
    xaxis_title="Dataset Size (elements)",
    yaxis_title="Execution Time (ms)",
    legend=dict(
        orientation='h', 
        yanchor='bottom', 
        y=1.02, 
        xanchor='center', 
        x=0.5
    ),
    hovermode='x unified',
    template='plotly_white'
)
fig1.update_xaxes(type='log')
fig1.update_yaxes(type='log')

fig1.write_image("execution_time_random.png", width=1200, height=600)

x_labels = ['100\n(random)', '25k\n(random)', '50k\n(random)', 
            '75k\n(random)', '100k\n(random)', '100k\n(almost)', 
            '100k\n(reversed)']

fig2 = go.Figure()
fig2.add_trace(go.Bar(
    x=x_labels, 
    y=insertion_sort, 
    name='Insertion Sort'
))
fig2.add_trace(go.Bar(
    x=x_labels, 
    y=selection_sort, 
    name='Selection Sort'
))
fig2.add_trace(go.Bar(
    x=x_labels, 
    y=merge_sort, 
    name='Merge Sort'
))
fig2.add_trace(go.Bar(
    x=x_labels, 
    y=radix_sort, 
    name='Radix Sort'
))

fig2.update_layout(
    title="Algorithm Performance Across All Datasets",
    xaxis_title="Dataset",
    yaxis_title="Execution Time (ms)",
    barmode='group',
    legend=dict(
        orientation='h', 
        yanchor='bottom', 
        y=1.02, 
        xanchor='center', 
        x=0.5
    ),
    hovermode='x unified',
    template='plotly_white'
)
fig2.update_yaxes(type='log')

fig2.write_image("performance_comparison.png", width=1200, height=600)

categories = ['Random', 'Almost Sorted', 'Reversed']
insertion_100k = [57103.20, 8586.48, 119193.48]
selection_100k = [69074.44, 68322.64, 59373.96]
merge_100k = [41.60, 28.50, 15.36]
radix_100k = [44.56, 57.16, 35.65]

fig3 = go.Figure()
fig3.add_trace(go.Bar(
    x=categories, 
    y=insertion_100k, 
    name='Insertion Sort'
))
fig3.add_trace(go.Bar(
    x=categories, 
    y=selection_100k, 
    name='Selection Sort'
))
fig3.add_trace(go.Bar(
    x=categories, 
    y=merge_100k, 
    name='Merge Sort'
))
fig3.add_trace(go.Bar(
    x=categories, 
    y=radix_100k, 
    name='Radix Sort'
))

fig3.update_layout(
    title="Input Sensitivity Comparison (100,000 Elements)",
    xaxis_title="Input Type",
    yaxis_title="Execution Time (ms)",
    barmode='group',
    legend=dict(
        orientation='h', 
        yanchor='bottom', 
        y=1.02, 
        xanchor='center', 
        x=0.5
    ),
    hovermode='x unified',
    template='plotly_white'
)
fig3.update_yaxes(type='log')

fig3.write_image("input_sensitivity.png", width=1200, height=600)